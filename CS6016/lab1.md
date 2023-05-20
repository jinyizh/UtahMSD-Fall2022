# Lab 1: Relational Model and Keys 
## Part 1 - English to Schema
*For each requirement specification, describe a table or set of tables that would represent the data using a relational model, and avoid data duplication as much as possible. Recall that a schema only specifies the column headers (attributes) in a table, and does not have any "instance" information in it. For this part, do not think in terms of SQL, just abstract schema design. Use the following format to specify your schema:*
```sql
Table Name [key (type), attribute1 (type), attribute2 (type), ...]
```
---
- A student has a student ID, a name, and a GPA.
```sql
Student [__sID (integer)__, name (string), GPA (real)]
```
- A grocery store needs to track an inventory of products for sale. It has zero or more of each type of product for sale, and needs to track the quantity and price for each product. A product has a name and a "stock keeping unit" (SKU) (hint: this is a real thing, you may google it). Remember that a valid table instance can't have duplicate rows - the store does not care about differentiating between individual items of the same product type, but it does want to be able to count them.
```sql
Product [name (string), __SKU (string)__, quantity (real), price (real)]
```