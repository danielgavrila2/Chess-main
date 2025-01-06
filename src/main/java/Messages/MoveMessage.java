package Messages;

import Chess_Business.Pieces.Coordinate;

/**
 * Here we keep information about the movements.
 * We have to keep track only of 3 parameters:
 * - isPieceKilled for updating the tile and the list of killed pieces.
 * - destinationCoordinate and currentCoordinate for obvious reasons.
 * */

public class MoveMessage implements java.io.Serializable{

    public boolean isPieceKilled;
    public Coordinate destinationCoordinate;
    public Coordinate currentCoordinate;

}
