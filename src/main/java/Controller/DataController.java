package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import DBConn.MySqlconn;
import Data.singleRec;
import View.JsonAndView;



@Controller
@RequestMapping(value="/Data")
public class DataController {
	
	@RequestMapping(value = "/getData", method = RequestMethod.GET)
	@ResponseBody
	public JsonAndView getData(HttpServletRequest request) {
		System.out.println("in control...");
		MySqlconn myconn=new MySqlconn();
		List<singleRec> data=new ArrayList<singleRec>();
		try {
			data = myconn.select();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new JsonAndView("sql error");
		}
		if(data==null)
			return new JsonAndView("none datas");
		List<singleRec> result=new ArrayList<singleRec>();
		for(int i=0;i<24;i++){
			singleRec sr=new singleRec();
			result.add(sr);
		}
		for(int i=0;i<data.size();i++){
			String hour=data.get(i).getSj();
			hour=hour.substring(hour.indexOf('_')+1);
			hour=hour.substring(0, hour.indexOf(':'));
			result.set(Integer.valueOf(hour), data.get(i));
		}
		JsonAndView jsonAndView = new JsonAndView();
		jsonAndView.addData("Datas", result);
		return jsonAndView;
	}
	
}
