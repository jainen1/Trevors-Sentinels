package net.trevorskullcrafter.util;

import com.google.gson.JsonObject;

import java.awt.*;

public class JsonTextObject {
	public JsonObject object;
	public JsonTextObject(){ object = new JsonObject(); }
	public JsonTextObject(TextUtil.Translation parent){
		object = new JsonObject();
		if(parent.rawText != null) { text(parent.rawText); }
		if(parent.textColor != null && parent.textColor.length == 1) { color(parent.textColor[0]); }
		if(parent.italic) { italic(true); }
		if(parent.bold) { bold(true); }
		if(parent.underlined) { underlined(true); }
		if(parent.strikethrough) { strikethrough(true); }
		if(parent.obfuscated) { obfuscated(true); }
	}
	
	public JsonObject build() { return object; }

	public JsonTextObject property(String key, String value){ object.addProperty(key, value); return this; }
	public JsonTextObject property(String key, Number value){ object.addProperty(key, value); return this; }
	public JsonTextObject property(String key, Boolean value){ object.addProperty(key, value); return this; }
	public JsonTextObject property(String key, Character value){ object.addProperty(key, value); return this; }

	public JsonTextObject text(String text) { return property("text", text); }
	public JsonTextObject index(int index) { return property("index", index); }
	public JsonTextObject translatable(String translatable) { return property("translate", translatable); }
	public JsonTextObject color(String color) { return property("color", color); }
	public JsonTextObject color(Color color) { return color("#" + Integer.toHexString(color.getRGB()).substring(2)); }
	public JsonTextObject italic(boolean value) { return property("italic", value); }
	public JsonTextObject bold(boolean bold) { return property("bold", bold); }
	public JsonTextObject underlined(boolean underlined) { return property("underlined", underlined); }
	public JsonTextObject strikethrough(boolean strikethrough) { return property("strikethrough", strikethrough); }
	public JsonTextObject obfuscated(boolean obfuscated) { return property("obfuscated", obfuscated); }
}
