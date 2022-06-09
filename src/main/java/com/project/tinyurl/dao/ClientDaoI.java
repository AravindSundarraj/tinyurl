package com.project.tinyurl.dao;

import com.project.tinyurl.domain.Clients;
import org.apache.ibatis.annotations.Insert;

public interface ClientDaoI {

    @Insert("insert into(client_name,total_urls,createdBy,createdTime) clients values(#{clientName}," +
            "#{totalUrls},#{createdBy},#{createdTime})")
    void addClient(Clients client);
}
