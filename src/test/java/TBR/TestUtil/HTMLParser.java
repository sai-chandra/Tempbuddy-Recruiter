package TBR.TestUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HTMLParser {
	
        public HTMLParser() {
		// TODO Auto-generated constructor stub
		}

		//#idelement
		//.classelement
		//tag
		//tag[attribute]
		//[attribute]
		//[attribute="attrcontent"]

		public String getTagContent(String selector, String html){
		Document doc = Jsoup.parse(html);
		Elements elem = doc.select(selector);
		return elem.get(0).html().toString();
		}

		public String getTagAttr(String selector, String attr, String html){
		Document doc = Jsoup.parse(html);
		Elements elem = doc.select(selector);
		return elem.get(0).attr(attr).toString();
		}
		}

