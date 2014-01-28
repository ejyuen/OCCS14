package host.property;

import java.applet.Applet;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.util.*;
import java.net.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

/**
 * This was 'borrowed' from somewhere online (I forgot where)
 * Property Manager lists all the properties and their values
 * @author Matthew Li
 *
 */
public class PropertyList {
	/**
	 * 00  = -1 if cannot be owned, 0 if can be owned
	 * 01  = -1 if houses cannot be put on it, 0 if you can put houses
	 * 02  = price if no houses, just owned
	 * 03  = price for 1 house.
	 * 04  = price for 2 houses.
	 * 05  = price for 3 houses.
	 * 06  = price for 4 houses.
	 * 07  = price for 5 houses (a hotel)
	 * 08  = price to buy the property from the bank
	 * 09  = mortgage price.  what you get back when you sell the property
	 * 10  = price to buy a house.
	 * 11  = first property associated to make the group. used to see if they are all owned.
	 * 12  = second property associated to make the group.
	 */
	public final static int[][] Properties = {
//		  00  01  02  03  04  05    06   07  08  09  10  11  12
		{ -1, -1, -1, -1, -1, -1 ,  -1,  -1, -1, -1, -1, -1, -1},	//Go						0
		{  0,  0,  2, 10, 30,  90, 160, 250, 60, 30, 50,  3,  3},	//Mediterranean Avenue		1
		{ -1, -1, -1, -1, -1,  -1,  -1,  -1, -1, -1, -1, -1, -1},	//Community Chest			2
		{  0,  0,  4, 20, 60, 180, 320, 450, 60, 30, 50,  1,  1},	//Baltic Avenue				3
		{ -1, -1, -1, -1, -1,  -1,  -1,  -1, -1, -1, -1, -1, -1},	//Income Tax				4
		{  0, -1, 25, -1, -1,  -1,  -1,  -1,200,100, -1, -1, -1},	//Reading Railroad			5
		{  0,  0,  6, 30, 90, 270, 400, 550,100, 50, 50,  8,  9},	//Oriental Avenue			6
		{ -1, -1, -1, -1, -1,  -1,  -1,  -1, -1, -1, -1, -1, -1},	//Chance					7
		{  0,  0,  6, 30, 90, 270, 400, 550,100, 50, 50,  6,  9},	//Vermont Avenue			8
		{  0,  0,  8, 40,100, 300, 450, 600,120, 60, 50,  6,  8},	//Connecticut Avenue		9
		{ -1, -1, -1, -1, -1,  -1,  -1,  -1, -1, -1, -1, -1, -1},	//Jail						10
		{  0,  0, 10, 50,150, 450, 625, 750,140, 70,100, 13, 14},	//St. Charles Place			11
		{  0, -1, 24, 24, 24,  -1,  -1,  -1,150, 75, -1, 28, 28},	//Electric Company			12
		{  0,  0, 10, 50,150, 450, 625, 750,140, 70,100, 11, 14},	//States Avenue				13
		{  0,  0, 12, 60,180, 500, 700, 900,160, 80,100, 11, 13},	//Virginia Avenue			14
		{  0, -1, 25, -1, -1,  -1,  -1,  -1,200,100, -1, -1, -1},	//Pennsylvania Railroad		15
		{  0,  0, 14, 70,200, 550, 750, 950,180, 90,100, 18, 19},	//St. James Place			16
		{ -1, -1, -1, -1, -1,  -1,  -1,  -1, -1, -1, -1, -1, -1},	//Community Chest			17
		{  0,  0, 14, 70,200, 550, 750, 950,180, 90,100, 16, 19},	//Tennessee Avenue			18
		{  0,  0, 16, 80,220, 600, 800,1000,200,100,100, 16, 18},	//New York Avenue			19
		{ -1,  -1, -1, -1, -1,  -1,  -1,  -1, -1, -1, -1, -1, -1},	//Free Parking				20
		{  0,  0, 18, 90,250, 700, 875,1050,220,110,150, 23, 24},	//Kentucky Avenue			21
		{ -1, -1, -1, -1, -1,  -1,  -1,  -1, -1, -1, -1, -1, -1},	//Chance					22
		{  0,  0, 18, 90,250, 700, 875,1050,220,110,150, 21, 24},	//Indiana Avenue			23
		{  0,  0, 20,100,300, 750, 925,1100,240,120,150, 21, 23},	//Illinois Avenue			24
		{  0, -1, 25, -1, -1,  -1,  -1,  -1,200,100, -1, -1, -1},	//B & O RailRoad			25
		{  0,  0, 22,110,330, 800, 975,1150,260,130,150, 27, 29},	//Atlantic Avenue			26
		{  0,  0, 22,110,330, 800, 975,1150,260,130,150, 26, 29},	//Ventnor Avenue			27
		{  0, -1, 24, 24, 24,  -1,  -1,  -1,150, 75, -1, 12, 12},	//Water Works				28
		{  0,  0, 24,120,360, 850,1025,1200,280,140,150, 26, 27},	//Marvin Gardens			29
		{ -1, -1, -1, -1, -1,  -1,  -1,  -1, -1, -1, -1, -1, -1},	//Go to Jail				30
		{  0,  0, 26,130,390, 900,1100,1275,300,150,200, 32, 34},	//Pacific Avenue			31
		{  0,  0, 26,130,390, 900,1100,1275,300,150,200, 31, 34},	//North Carolina Avenue		32
		{ -1, -1, -1, -1, -1,  -1,  -1,  -1, -1, -1, -1, -1, -1},	//Community Chest			33
		{  0,  0, 28,150,450,1000,1200,1400,320,160,200, 31, 32},	//Pennsylvania Avenue		34
		{  0, -1, 25, -1, -1,  -1,  -1,  -1,200,100, -1, -1, -1},	//Short Line Railroad		35
		{ -1, -1, -1, -1, -1,  -1,  -1,  -1, -1, -1, -1, -1, -1},	//Chance					36
		{  0,  0, 35,175,500,1100,1300,1500,350,175,200, 39, 39},	//Park Place				37
		{ -1, -1, -1, -1, -1,  -1,  -1,  -1, -1, -1, -1, -1, -1},	//Luxury Tax				38
		{  0,  0, 50,200,600,1400,1700,2000,400,200,200, 37, 37}	//Board Walk				39
	};
	
	/**
	 * list of the names of the properties.
	 */
	public final static String[] List = {
		"Go",
		"Mediterranean Avenue",
		"Community Chest",
		"Baltic Avenue",
		"Income Tax",
		"Reading Railroad",
		"Oriental Avenue",
		"Chance",
		"Vermont Avenue",
		"Connecticut Avenue",
		"Jail",
		"St. Charles Place",
		"Electric Company",
		"States Avenue",
		"Virginia Avenue",
		"Pennsylvania Railroad",
		"St. James Place",
		"Community Chest",
		"Tennessee Avenue",
		"New York Avenue",
		"Free Parking",
		"Kentucky Avenue",
		"Chance",
		"Indiana Avenue",
		"Illinois Avenue",
		"B & O RailRoad",
		"Atlantic Avenue",
		"Ventnor Avenue",
		"Water Works",
		"Marvin Gardens",
		"Go to Jail",
		"Pacific Avenue",
		"North Carolina Avenue",
		"Community Chest",
		"Pennsylvania Avenue",
		"Short Line Railroad",
		"Chance",
		"Park Place",
		"Luxury Tax",
		"Board Walk"
	};
}
