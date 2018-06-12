package com.hepangda.ttms.idao;

import com.hepangda.ttms.model.ScheduleFetch;
import com.hepangda.ttms.util.QueryResult;

// id: (int)演出计划id,
//         time: (string)演出计划时间 格式yyyy-mm-dd hh:mm:ss,
//         stuname: (string)演出厅名字,
//         movname: (string)影片名字,
//         movimage: (string)影片图片url,
//         price: (int)场次价格
public interface IScheduleFetchDAO {
    QueryResult<ScheduleFetch> query(ScheduleFetch sf);
}