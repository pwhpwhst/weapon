package com.pwhTest.MFTest.MFCode;
/**
 * 代表魔方的一个格子
 * @author pwh-pc
 *
 */
public class Grid {
public byte content;
public Grid(byte content)
{
	this.content=content;
}

public boolean equals(Object o)
{
	if(o!=null)
	{
		if(((Grid)o).content==content)
			return true;		
	}
return false;		
}

public int hashCode()
{	
return content&0x1f;	
}
}