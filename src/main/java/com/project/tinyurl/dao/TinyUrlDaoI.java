package com.project.tinyurl.dao;

import com.project.tinyurl.domain.TinyUrl;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

@Mapper
public interface TinyUrlDaoI {

      @Select("SELECT url FROM tinyurl WHERE turl = #{turl}")
      String getClientUrl(@Param("turl") String turl);
      @Insert("insert into tinyurl (turl,url,client,created_by)  values(#{turl}," +
              "#{url},#{client},#{createdBy})")
      void addUrl(TinyUrl tinyurl);
}
