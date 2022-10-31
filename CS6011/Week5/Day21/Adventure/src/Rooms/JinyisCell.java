package Rooms;

import Game.Adventure;
import Items.Item;

// Done by Jinyi Zhou

public class JinyisCell extends Room{

    private boolean locked_ = false;
    private boolean lightOn_ = false;

    public JinyisCell() {
        super("Jinyi's Room", "This is Jinyi's Room");
        Item batteryForJaysCell = new Item("Battery to Jay's Cell", "The Key to Jay's Cell");
        items_.add(batteryForJaysCell);
    }

    @Override
    public Room goThroughDoor(int doorNum) {

        if( locked_ ) {
            System.out.println( "The door is locked!" );
            return null;
        }
        else {
            return super.goThroughDoor( doorNum );
        }
    }

    @Override
    public void playerEntered() {
        if( locked_ ) {
            System.out.println( "You hear a clicking noise..." );
        } else {
            System.out.println( "The light is off" );
        }
    }

    @Override
    public boolean handleCommand( String[] subcommands ) {

        if( subcommands.length <= 1 ) {
            return false;
        }
        String cmd  = subcommands[0];
        String attr = subcommands[1];

        // turn light, use
        if( cmd.equals( "unlock" ) && attr.equals( "door") ) {

            boolean hasKey = false;
            for( Item item : Adventure.inventory ) {
                if( item.getName().equals( "Key" ) ) {
                    hasKey = true;
                    break;
                }
            }
            if( hasKey ) {
                System.out.println( "You unlock the door.");
                locked_ = false;
            }
            else {
                System.out.println( "You don't have a key." );
            }
            return true;
        }
        //
        if( cmd.equals( "turn" ) && attr.equals( "light") ) {

            boolean hasBattery = false;
            for( Item item : Adventure.inventory ) {
                if( item.getName().equals( "Battery to Jinyi's Cell")) {
                    hasBattery = true;
                    break;
                }
            }
            if( hasBattery ) {
                System.out.println( "You turned on the light.");
            }
            else {
                System.out.println( "You need a battery to turn on the light." );
            }
            return true;
        }
        return false;
    }
}
