package com.quartz.boot.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quartz.boot.excel.Poi;
import com.quartz.boot.service.ReqInfoService;
import com.quartz.boot.vo.DeptLCResponseVO;
import com.quartz.boot.vo.DoubleResponseVO;
import com.quartz.boot.vo.IntResponseVO;
import com.quartz.boot.vo.RequestInfoVO;
import com.quartz.boot.vo.Term;


@RestController
@RequestMapping("api/v2/login-cnt")
public class LoginCntController {
	
	@Autowired
	private ReqInfoService reqInfoService;
	
//	//연도 별 총 로그인 수
//	@RequestMapping(value = "/year/{year}", method = RequestMethod.GET)
//	public HashMap<String, Object> getLCbyYear(@PathVariable("year") String year){
//		HashMap<String, Object> response = new HashMap<String, Object>();
//		
//		List<RequestInfoVO> list = null;
//		try {
//			list = reqInfoService.selectLCByYear(year);
//			response.put("data-size", 1);
//			response.put("data", list.size());
//			response.put("status", "200");
//			response.put("description", "월별 일평균 로그인 수");
//			response.put("year", year);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		poi.writeExcel(response);
//		
//		System.out.println(response.toString());
//		return response;
//	}
	
	//월별 일평균 로그인 수
	@RequestMapping(value = "/month/day-avg", method = RequestMethod.GET)
	public HashMap<String, Object> getDayAvgLCbyMonth(
								@RequestParam(value = "begin-month", required = true) String beginMonth,
								@RequestParam(value = "end-month", required = true) String endMonth) throws Exception{
		
		Term term = new Term(beginMonth, endMonth);
		
		return reqInfoService.selectDayAvgLCByMonth(term);
	}
	
	/* 월별 휴일 제외 로그인 수 */
	@RequestMapping(value = "/month/exc-weekend", method = RequestMethod.GET)
	public HashMap<String, Object> getExcOffDayLCbyMonth(
						@RequestParam(value = "begin-month", required = true) String beginMonth,
						@RequestParam(value = "end-month", required = true) String endMonth) throws Exception{

		Term term = new Term(beginMonth, endMonth);

		return reqInfoService.selectExcOffDayLCByMonth(term);
	}
	
	//부서 별 로그인 수
	@RequestMapping(value = "/month/dept", method = RequestMethod.GET)
	public HashMap<String, Object> getDeptLCbyMonth(
								@RequestParam(value = "month", required = true) String month) throws Exception{
								

		return reqInfoService.selectDeptLCByMonth(month);
	}
	
	


}
