package com.maxlong.study.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/2/13 14:06
 * 类说明
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}