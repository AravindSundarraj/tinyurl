package com.project.tinyurl.dao;

import com.project.tinyurl.domain.Clients;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
public interface ClientDaoI {

    @Insert("insert into clients(client_name,total_urls,created_by)  values(#{clientName}," +
            "#{totalUrls},#{createdBy})")
    void addClient(Clients client);

    @Select("select client_name,total_urls from clients where client_name=#{clientName}")
    @Results(value = {
            @Result(property = "clientName", column = "client_name"),
            @Result(property="totalUrls", column = "total_urls"),
    })
    Clients getClient(@Param("clientName") String clientName);

    @Update("update clients set total_urls=#{totalUrls} where client_name=#{clientName}")
    int updateClient(@Param("clientName") String clientName , @Param("totalUrls") int totalUrls);
}
