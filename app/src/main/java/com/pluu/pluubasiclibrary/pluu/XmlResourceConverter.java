package com.pluu.pluubasiclibrary.pluu;

import java.io.IOException;
import java.util.HashMap;

import android.content.res.XmlResourceParser;
import android.text.TextUtils;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.xmlpull.v1.XmlPullParserException;

/**
 * Created by PLUUSYSTEM on 2015-01-29.
 */
public class XmlResourceConverter {

	private HashMap<String, HashMap<String, String>> data;

	public XmlResourceConverter() {
		init();
	}

	private void init() {
		data = Maps.newHashMap();

		HashMap<String, String> subData;

		subData = Maps.newHashMap();
		subData.put("-1", "match_parent");
		subData.put("-2", "wrap_content");
		data.put("layout_width", subData);

		subData = Maps.newHashMap();
		subData.put("-1", "match_parent");
		subData.put("-2", "wrap_content");
		data.put("layout_height", subData);

		subData = Maps.newHashMap();
		subData.put("0", "horizontal");
		subData.put("1", "vertical");
		data.put("orientation", subData);

		subData = Maps.newHashMap();
		subData.put("0x50", "bottom");
		subData.put("0x11", "center");
		subData.put("0x1", "center_horizontal");
		subData.put("0x10", "center_vertical");
		data.put("layout_gravity", subData);

		subData = Maps.newHashMap();
		subData.put("0x50", "bottom");
		subData.put("0x11", "center");
		subData.put("0x1", "center_horizontal");
		subData.put("0x10", "center_vertical");
		data.put("gravity", subData);

		subData = Maps.newHashMap();
		subData.put("2", "gone");
		subData.put("4", "invisible");
		subData.put("0", "visible");
		data.put("visibility", subData);

		subData = Maps.newHashMap();
		for (TextUtils.TruncateAt truncateAt : TextUtils.TruncateAt.values()) {
			subData.put(String.valueOf(truncateAt.ordinal() + 1), truncateAt.name().toLowerCase());
		}
		data.put("ellipsize", subData);

		subData = Maps.newHashMap();
		subData.put("4", "center");
		subData.put("1", "gravity");
		subData.put("0", "inherit");
		subData.put("3", "textEnd");
		subData.put("2", "textStart");
		subData.put("6", "viewEnd");
		subData.put("5", "viewStart");
		data.put("textAlignment", subData);

	}

	public String convert(XmlResourceParser parser) {
		int type = -1;
		String indentString = "    ";

		boolean isOpenTag = false;

		StringBuilder xml = new StringBuilder();

		while(type != XmlResourceParser.END_DOCUMENT) {
			try {
				type = parser.getEventType();
				switch(type) {
					case XmlResourceParser.START_TAG:
						if (isOpenTag) {
							xml.append(">\n");
						}

						isOpenTag = true;

						xml.append(Strings.repeat(indentString, parser.getDepth() - 1));

						xml.append("<");
						xml.append(parser.getName());
						for(int i = 0; i < parser.getAttributeCount(); i++) {
							String name = parser.getAttributeName(i);
							xml.append(" ")
								.append(name)
								.append("=\"");

							String value = parser.getAttributeValue(i);
							if (data.containsKey(name)) {
								HashMap convertValues = data.get(name);
								if (convertValues.containsKey(value)) {
									xml.append(convertValues.get(value));
								} else {
									xml.append(value);
								}
							} else {
								xml.append(value);
							}

							xml.append("\"");
						}
						break;
					case XmlResourceParser.END_TAG:
						if (isOpenTag) {
							xml.append(" />\n");
							isOpenTag = false;
						} else {
							xml.append(Strings.repeat(indentString, parser.getDepth() - 1));
							xml.append("</");
							xml.append(parser.getName());
							xml.append(">\n");
						}

						break;
				}
				parser.next();
			} catch(XmlPullParserException | IOException e) {
				e.printStackTrace();
			}
		}
		parser.close();;

		return xml.toString();
	}
}
