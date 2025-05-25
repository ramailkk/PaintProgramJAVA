import java.io.Serializable;

public class Point implements Serializable
{
	int x;
	int y;
	
	Point()
	{
		x=y=0;
	}
	
	Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	Point(Point p)
	{
		x = p.x;
		y = p.y;
	}
}
