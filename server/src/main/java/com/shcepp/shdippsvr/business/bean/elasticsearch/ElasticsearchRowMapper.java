package com.shcepp.shdippsvr.business.bean.elasticsearch;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

/**
 *  _source transform to javaBean
 * @author kzhou
 * @date2018/11/20
 */
public interface ElasticsearchRowMapper<T> {
    T mapRow(SearchResponse response);
    T mapRow(SearchHit[] hits);
}
