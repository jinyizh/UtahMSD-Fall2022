#include <atomic>
#include <chrono>
#include <iostream>
#include <thread>
#include <vector>
#include "/opt/homebrew/opt/libomp/include/omp.h"

template<typename T>
struct Result {
    T sum; // result of the calculation
    int duration; // duration of the calculation
};

Result<int> sum_no_thread( int arr[], int N) { // control group
    auto start = std::chrono::high_resolution_clock::now();
    int sum = 0;
    for (int i = 0; i < N; ++i)
        sum += arr[i];
    auto stop = std::chrono::high_resolution_clock::now();
    Result<int> result{};
    result.sum = sum;
    result.duration = duration_cast<std::chrono::microseconds>(stop - start).count();
    return result;
}

template<typename T>
auto parallel_sum_std(T arr[], size_t N, size_t num_of_threads) {
    num_of_threads %= N;
    auto start = std::chrono::high_resolution_clock::now();
    size_t remainder = N % num_of_threads;
    size_t num_per_thread = N / num_of_threads;

    std::vector<T> sumArr(num_of_threads, 0);
    std::vector<std::thread> threads;
    threads.reserve(num_of_threads);
    std::atomic<T> sum = 0;
    for (int i = 0; i < num_of_threads; i++) {
        threads.emplace_back([&, arr, i] () {
            for (int j = i * num_per_thread; j < i * num_per_thread + num_per_thread; j++)
                sumArr[i] += arr[j];
            sum += sumArr[i];
        });
    }

    for (int i = 0; i < num_of_threads; ++i)
        threads[i].join();
    if (remainder > 0)
        for (int i = num_of_threads * num_per_thread; i < N; i++)
            sum += arr[i];

    auto stop = std::chrono::high_resolution_clock::now();
    Result<T> result{};
    result.sum = sum;
    result.duration = duration_cast<std::chrono::microseconds>(stop - start).count();
    return result;
}

// ---------------------------------------- //

template<typename T>
auto parallel_sum_omp_cust(T arr[], size_t N, size_t num_of_threads) {
    num_of_threads %= N;
    std::atomic<T> sum = 0;
    std::vector<T> partialSums(num_of_threads, 0);
    auto start = std::chrono::high_resolution_clock::now();
    size_t remainder = N % num_of_threads;
    size_t num_per_thread = N / num_of_threads;
    omp_set_dynamic(0);
    omp_set_num_threads(num_of_threads);

#pragma omp parallel
    {
        int id = omp_get_thread_num();
        for (int j = id * num_per_thread; j < id * num_per_thread + num_per_thread; ++j)
            partialSums[id] += arr[j];
    }

#pragma omp single
    {
        for (int i = 0; i < num_of_threads; i++)
            sum += partialSums[i];
        if (remainder > 0)
            for (int i = num_of_threads * num_per_thread; i < N; i++)
                sum += arr[i];
    }

    auto stop = std::chrono::high_resolution_clock::now();
    Result<T> result{};
    result.sum = sum;
    result.duration = duration_cast<std::chrono::microseconds>(stop - start).count();
    return result;
}

// --------------------------------------------- //

template<typename T>
auto parallel_sum_omp_builtin(T a[], size_t N, size_t num_of_threads) {
    int sum = 0;
    auto start = std::chrono::high_resolution_clock::now();
    omp_set_dynamic(0);
    omp_set_num_threads(num_of_threads);

#pragma omp parallel for num_threads(threads) reduction(+:sum)
    {
        for (int i = 0; i < N; i++)
            sum += a[i];
    }

    auto stop = std::chrono::high_resolution_clock::now();
    Result<T> result{};
    result.sum = sum;
    result.duration = duration_cast<std::chrono::microseconds>(stop - start).count();
    return result;
}

// ---------------------------------------------- //

int main() {
    int size = 16000;
    int arr[size];
    for (int i = 0; i < size; ++i)
        arr[i] = i;
    size_t num_of_threads = 16;
    Result<int> result_thread = parallel_sum_std(arr, size, num_of_threads);
    Result<int> result_thread_omp = parallel_sum_omp_cust(arr, size, num_of_threads);
    Result<int> result_thread_omp_reduct = parallel_sum_omp_builtin(arr, size, num_of_threads);
    Result<int> result_no_thread = sum_no_thread(arr, size);

    std::cout << "Control group: Sum without thread" << std::endl;

    std::cout << result_no_thread.sum << std::endl;
    std::cout << result_no_thread.duration << std::endl;

    std::cout << "Part 1: C++ Parallel Reduction" << std::endl;

    std::cout << result_thread.sum << std::endl;
    std::cout << result_thread.duration << std::endl;

    std::cout << "Part 2: OpenMP Custom Parallel Reduction" << std::endl;

    std::cout << result_thread_omp.sum << std::endl;
    std::cout << result_thread_omp.duration << std::endl;

    std::cout << "Part 3: OpenMP Built-in Reduction" << std::endl;

    std::cout << result_thread_omp_reduct.sum << std::endl;
    std::cout << result_thread_omp_reduct.duration << std::endl;

    return 0;
}