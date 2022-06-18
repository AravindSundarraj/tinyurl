package com.project.tinyurl.dao;

import com.project.tinyurl.domain.Clients;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
public interface ClientDaoI {

    @Insert("insert into clients(client_name,total_urls,created_by)  values(#{clientName}," +
            "#{totalUrls},#{createdBy})")
    void addClient(Clients client);

    @Select("SELECT * FROM TINYURLS WHERE tinyurl = #{tinyUrl}")
    Clients getClient(@Param("clientName") String clientName);

    @Update("update clients set total_urls=#{totalUrls} where ")
    //@Select("SELECT * FROM clients WHERE tinyurl = #{tinyUrl}")
    Clients updateClient(@Param("clientName") String clientName);
}
