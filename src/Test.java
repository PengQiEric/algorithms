

class Contents
{
	int i ;
	public Contents(int i)
	{
		this.i = i;
	}
	public int value()
	{
		return i;
	}
}

class Parallel
{
	public Contents cont(int i)
	{
		return new Contents(i)
		{
			public int value()
			{
				return super.value()*37;
			}
		};
	}
}
public class Test
{
	public static void main(String[] args)
	{
		Parallel p = new Parallel();
		Contents c = p.cont(10);
		System.out.println(c.value());
	}
}