package com.project.tinyurl.dao;

import com.project.tinyurl.domain.TinyUrl;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

@Mapper
public interface TinyUrlDaoI {

      @Select("SELECT url FROM TINYURLS WHERE tinyurl = #{tinyUrl}")
      String getClientUrl(@Param("tinyurl") String tinyUrl);
      @Insert("insert into(tinyurl,url,client,createdBy,createdTime) tinyurls values(#{tinyurl}," +
              "#{url},#{client},#{createdBy},#{createdTime})")
      void addUrl(TinyUrl tinyurl);
}
