package com.pwhTest.MFTest.MFCode;

import java.util.HashMap;
import java.util.Map;

public class MFCodeStaticImpl implements IMFCodeStatic {

	public MFCodeStaticImpl() {
		LOCATION_TO_ID = new HashMap<Grid, Grid>();
		ID_TO_LOCATION = new HashMap<Grid, Grid>();
		initLOCATION_TO_ID(LOCATION_TO_ID, ID_TO_LOCATION);
	}

	public void showIdToLocation(int id) {
		byte b = ID_TO_LOCATION.get(new Grid((byte) id)).content;
		System.out.print((b & 0xc0) >> 6);
		System.out.print((b & 0x30) >> 4);
		System.out.print((b & 0x0C) >> 2);
		System.out.print((b & 0x03) >> 0);
		System.out.print("\n");
	}

	public byte getLocation(int id) {
		return ID_TO_LOCATION.get(new Grid((byte) id)).content;
	}

	public void showLocationToId(int location) {
		byte b = LOCATION_TO_ID.get(new Grid((byte) location)).content;
		System.out.print(b);
		System.out.print("\n");
	}

	public byte getIdFromLocation(int location) {
		return LOCATION_TO_ID.get(new Grid((byte) location)).content;
	}

	public Map<Byte, Byte> gridToCols(byte grid) {
		Map<Byte, Byte> resultMap = new HashMap<Byte, Byte>();
		byte[] _a = { 0x30, 0x0c, 0x03 };
		for (int i1 = 0; i1 <= 2; i1++) {
			switch ((grid & _a[i1]) >> (4 - 2 * i1)) {
			case 0:
				resultMap.put(Byte.valueOf((byte) i1), Byte.valueOf((byte) 7));
				break;
			case 1:
				resultMap.put(Byte.valueOf((byte) i1),
						Byte.valueOf((byte) (2 * i1)));
				break;
			case 3:
				resultMap.put(Byte.valueOf((byte) i1),
						Byte.valueOf((byte) (2 * i1 + 1)));
				break;
			default:
			}
		}
		return resultMap;
	}

	public void initLOCATION_TO_ID(Map<Grid, Grid> _LOCATION_TO_ID,
			Map<Grid, Grid> _ID_TO_LOCATION) {
		byte[] _b = { 0x11, 0x14, 0x13, 0x1c, 0x15, 0x17, 0x1f, 0x1d, 0x05,
				0x07, 0x0f, 0x0d, 0x31, 0x34, 0x33, 0x3c, 0x35, 0x37, 0x3f,
				0x3d };
		for (byte i1 = 1; i1 <= 20; i1++) {
			_LOCATION_TO_ID.put(new Grid(_b[i1 - 1]), new Grid((byte) i1));
			_ID_TO_LOCATION.put(new Grid((byte) i1), new Grid(_b[i1 - 1]));
		}
	}

	public int getActualPos(int locationId, int status) {
		if (locationId < 5) {
			return 2 * locationId + status - 2;
		} else if (locationId < 9) {
			return 3 * locationId + status - 7;
		} else if (locationId < 17) {
			return 2 * locationId + status + 2;
		} else
			return 3 * locationId + status - 15;
	}

	public Map<Grid, Grid> LOCATION_TO_ID;

	public Map<Grid, Grid> ID_TO_LOCATION;

	// 静态成员变量
	public final byte[][] ID_TO_LOCATION_VIEW = { { 1, 9, 9, 9, 7, 9 },
			{ 5, 9, 3, 9, 9, 9 }, { 7, 9, 9, 9, 9, 1 }, { 3, 9, 9, 5, 9, 9 },

			{ 2, 9, 0, 9, 8, 9 }, { 8, 9, 6, 9, 9, 2 }, { 6, 9, 9, 8, 9, 0 },
			{ 0, 9, 9, 2, 6, 9 },

			{ 9, 9, 1, 9, 5, 9 }, { 9, 9, 7, 9, 9, 5 }, { 9, 9, 9, 7, 9, 3 },
			{ 9, 9, 9, 1, 3, 9 },

			{ 9, 1, 9, 9, 1, 9 }, { 9, 3, 5, 9, 9, 9 }, { 9, 7, 9, 9, 9, 7 },
			{ 9, 5, 9, 3, 9, 9 },

			{ 9, 0, 2, 9, 2, 9 }, { 9, 6, 8, 9, 9, 8 }, { 9, 8, 9, 6, 9, 6 },
			{ 9, 2, 9, 0, 0, 9 } };

	/**
	 * LOCATION_VIEW_IDS用法：<br>
	 * LOCATION_VIEW_IDS[0]←→你现在正对着魔方的白面<br>
	 * 815 ←→数字代表grid号<br>
	 * 5白2<br>
	 * 736<br>
	 */
	public final byte[][] LOCATION_VIEW_TO_IDS = { { 8, 1, 5, 4, 2, 7, 3, 6 },
			{ 17, 13, 20, 14, 16, 18, 15, 19 }, { 5, 9, 17, 2, 14, 6, 10, 18 },
			{ 20, 12, 8, 16, 4, 19, 11, 7 }, { 20, 13, 17, 12, 9, 8, 1, 5 },
			{ 7, 3, 6, 11, 10, 19, 15, 18 }, };

	/**
	 * NORMALIZE_GRIDS的作用是将标准grid具体化为普通grid<br>
	 * For example：将标准二型格具体化成格号为0x05(即拥有红绿两面的二型格)的grid的步骤如下：<br>
	 * 1、通过LOCATION_TO_ID表得出格号为0x05的grid的ID为0x09<br>
	 * 2、观察STANDARDIZE_GRIDS，可得到NORMALIZE_GRIDS[9-1][]={4,7,2}<br>
	 * 3、{白，Y，红},这个数组表示标准二型格拥有的两种颜色<br>
	 * {x,y,z}<br>
	 * {4,7,2}<br>
	 * {红，X,绿}<br>
	 * 标准二型格具体化成格号为0x05的小格子后，标准格的白色对应具体化后的红色，标准格的红色对应具体化后的绿色，
	 */
	public final byte[][] NORMALIZE_GRIDS = { { 0, 7, 4 }, { 2, 7, 0 },
			{ 5, 7, 0 }, { 0, 7, 3 }, { 0, 2, 4 }, { 0, 5, 2 }, { 0, 3, 5 },
			{ 0, 4, 3 }, { 4, 7, 2 }, { 2, 7, 5 }, { 5, 7, 3 }, { 3, 7, 4 },
			{ 4, 7, 1 }, { 1, 7, 2 }, { 1, 7, 5 }, { 3, 7, 1 }, { 1, 4, 2 },
			{ 1, 2, 5 }, { 1, 5, 3 }, { 1, 3, 4 } };

	/**
	 * STANDARDIZE_GRIDS的作用是将普通grid泛化为标准二型格(格号0x11，拥有红白两面，其Y轴与魔方主体的Y轴方向一致)
	 * 或标准三型格(格号0x15，拥有红白绿三面，其白面法线方向与魔方主体的x轴方向一致)。<br>
	 * For example：泛化格号为0x05(即拥有红绿两面的二型格)的grid的步骤如下：<br>
	 * 1、通过LOCATION_TO_ID表得出格号为0x05的grid的ID为0x09<br>
	 * 2、观察STANDARDIZE_GRIDS，可得到STANDARDIZE_GRIDS[9-1][]={3,2,0}<br>
	 * 3、{X，绿，红},这个数组表示格号为0x05拥有的两种颜色<br>
	 * {x,y,z}<br>
	 * {3,2,0}<br>
	 * {X,红，白}<br>
	 * 0x05格泛化后，原来的绿色对应标准格的红色，原来的红色对应标准格的白色
	 */
	public final byte[][] STANDARDIZE_GRIDS = { { 0, 7, 4 }, { 4, 0, 7 },
			{ 4, 7, 0 }, { 0, 4, 7 }, { 0, 2, 4 }, { 0, 4, 2 }, { 0, 2, 4 },
			{ 0, 4, 2 }, { 7, 4, 0 }, { 7, 0, 4 }, { 7, 4, 0 }, { 7, 0, 4 },
			{ 4, 7, 0 }, { 0, 4, 7 }, { 0, 7, 4 }, { 4, 0, 7 }, { 0, 4, 2 },
			{ 0, 2, 4 }, { 0, 4, 2 }, { 0, 2, 4 } };

	/**
	 * STD_GRID_COL_TO_LOCAL_COL数组是为了解决这样一个问题：<br>
	 * 某标准grid位于某location上，且status是某特定值时，标准grid的色块位于魔方的哪些面上<br>
	 * For example：标准二型格在位置号为0x05的location，且状态为1时，标准grid的色块位于魔方的哪些面上？<br>
	 * 1、先使用 getActualPos方法，获得pos=21<br>
	 * 2、取STD_GRID_COL_TO_LOCAL_COL[21][]={2,7,4}<br>
	 * 3、{X,Y,Z}，这里代表标准格小面的法线方向<br>
	 * {白、Y、红}，这里代表标准二型格所拥有的颜色<br>
	 * {2,7,4}<br>
	 * {绿、Y、红}<br>
	 * 标准二型格在位置号为0x05的location，且状态为1时，其白色的小面位于魔方的绿面上，红色的小面位于魔方红色的面上
	 */
	public final byte[][] STD_GRID_COL_TO_LOCAL_COL = { { 0, 7, 4 },
			{ 4, 7, 0 }, { 2, 7, 0 }, { 0, 7, 2 }, { 5, 7, 0 }, { 0, 7, 5 },
			{ 0, 7, 3 }, { 3, 7, 0 }, { 0, 2, 4 }, { 2, 4, 0 }, { 4, 0, 2 },
			{ 0, 5, 2 }, { 2, 0, 5 }, { 5, 2, 0 }, { 0, 3, 5 }, { 3, 5, 0 },
			{ 5, 0, 3 }, { 0, 4, 3 }, { 3, 0, 4 }, { 4, 3, 0 }, { 4, 7, 2 },
			{ 2, 7, 4 }, { 2, 7, 5 }, { 5, 7, 2 }, { 5, 7, 3 }, { 3, 7, 5 },
			{ 3, 7, 4 }, { 4, 7, 3 }, { 4, 7, 1 }, { 1, 7, 4 }, { 1, 7, 2 },
			{ 2, 7, 1 }, { 1, 7, 5 }, { 5, 7, 1 }, { 3, 7, 1 }, { 1, 7, 3 },
			{ 1, 4, 2 }, { 2, 1, 4 }, { 4, 2, 1 }, { 1, 2, 5 }, { 2, 5, 1 },
			{ 5, 1, 2 }, { 1, 5, 3 }, { 3, 1, 5 }, { 5, 3, 1 }, { 1, 3, 4 },
			{ 3, 4, 1 }, { 4, 1, 3 } };

	/**
	 * LOCAL_COL_TO_STD_GRID_COL数组是为了解决这样一个问题：<br>
	 * 某标准grid位于某location上，且status是某特定值时，魔方的各个面上拥有标准grid的哪些色块<br>
	 * For example：标准二型格在位置号为0x05的location，且状态为1时，魔方的各个面上有标准grid的哪些颜色？<br>
	 * 1、先使用 getActualPos方法，获得pos=21<br>
	 * 2、取LOCAL_COL_TO_STD_GRID_COL[21][]={7,7,0,7,4,7}<br>
	 * 3、{白，黄，绿，蓝，红，橙}，这里代表魔方的六个面<br>
	 * {7,7,0,7,4,7}<br>
	 * {?,?,白,?,红,?}<br>
	 * 
	 * 标准二型格在位置号为0x05的location，且状态为1时，魔方的绿面上拥有标准grid的白面，魔方红色的面上拥有标准grid的红面
	 */
	public final byte[][] LOCAL_COL_TO_STD_GRID_COL = { { 0, 7, 7, 7, 4, 7 },
			{ 4, 7, 7, 7, 0, 7 }, { 4, 7, 0, 7, 7, 7 }, { 0, 7, 4, 7, 7, 7 },
			{ 4, 7, 7, 7, 7, 0 }, { 0, 7, 7, 7, 7, 4 }, { 0, 7, 7, 4, 7, 7 },
			{ 4, 7, 7, 0, 7, 7 }, { 0, 7, 2, 7, 4, 7 }, { 4, 7, 0, 7, 2, 7 },
			{ 2, 7, 4, 7, 0, 7 }, { 0, 7, 4, 7, 7, 2 }, { 2, 7, 0, 7, 7, 4 },
			{ 4, 7, 2, 7, 7, 0 }, { 0, 7, 7, 2, 7, 4 }, { 4, 7, 7, 0, 7, 2 },
			{ 2, 7, 7, 4, 7, 0 }, { 0, 7, 7, 4, 2, 7 }, { 2, 7, 7, 0, 4, 7 },
			{ 4, 7, 7, 2, 0, 7 }, { 7, 7, 4, 7, 0, 7 }, { 7, 7, 0, 7, 4, 7 },
			{ 7, 7, 0, 7, 7, 4 }, { 7, 7, 4, 7, 7, 0 }, { 7, 7, 7, 4, 7, 0 },
			{ 7, 7, 7, 0, 7, 4 }, { 7, 7, 7, 0, 4, 7 }, { 7, 7, 7, 4, 0, 7 },
			{ 7, 4, 7, 7, 0, 7 }, { 7, 0, 7, 7, 4, 7 }, { 7, 0, 4, 7, 7, 7 },
			{ 7, 4, 0, 7, 7, 7 }, { 7, 0, 7, 7, 7, 4 }, { 7, 4, 7, 7, 7, 0 },
			{ 7, 4, 7, 0, 7, 7 }, { 7, 0, 7, 4, 7, 7 }, { 7, 0, 4, 7, 2, 7 },
			{ 7, 2, 0, 7, 4, 7 }, { 7, 4, 2, 7, 0, 7 }, { 7, 0, 2, 7, 7, 4 },
			{ 7, 4, 0, 7, 7, 2 }, { 7, 2, 4, 7, 7, 0 }, { 7, 0, 7, 4, 7, 2 },
			{ 7, 2, 7, 0, 7, 4 }, { 7, 4, 7, 2, 7, 0 }, { 7, 0, 7, 2, 4, 7 },
			{ 7, 4, 7, 0, 2, 7 }, { 7, 2, 7, 4, 0, 7 } };
}
