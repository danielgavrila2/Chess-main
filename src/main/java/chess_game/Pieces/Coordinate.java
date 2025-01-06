package Chess_Business.Pieces;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Coordinate implements java.io.Serializable{
    private int x;
    private int y;
    
    public Coordinate(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString()
    {
        return "[X:"+x +", Y:"+y+"]";
    }

    @Override
    public boolean equals(Object obj) {
        return (((Coordinate)obj).getX() == this.x && ((Coordinate)obj).getY() == this.y); 
    }
    
    public Coordinate plus(Coordinate coord)
    {
        return new Coordinate(this.x + coord.getX(),this.y+coord.getY());
    }
}
