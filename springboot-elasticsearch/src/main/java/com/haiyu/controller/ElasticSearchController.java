package com.haiyu.controller;

import com.haiyu.entity.Person;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @Title: ElasticSearchController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/7/31 17:39
 */
@RestController
public class ElasticSearchController {

    @Autowired
    private TransportClient client;


    /**
     *
     * 功能描述: 增加数据
     *
     */
    @PostMapping("person/add")
    public ResponseEntity add(@RequestBody Person person) {
        try {
            XContentBuilder content = XContentFactory.jsonBuilder().startObject()
                    .field("name", person.getName())
                    .field("age", person.getAge())
                    .field("work", person.getWork())
                    .endObject();
            String id = String.valueOf(person.getId());
            IndexResponse result = this.client.prepareIndex("data", "person",id).setSource(content).get();
            return new ResponseEntity(result.getId(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 功能描述: 查找数据
     *
     */
    @GetMapping("person/get")
    public ResponseEntity get(@RequestParam(name = "id", defaultValue="") String id) {
        if (id.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        GetResponse result = client.prepareGet("data", "person", id).get();
        if (!result.isExists()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result.getSource(), HttpStatus.OK);
    }


    /**
     *
     * 功能描述: 更新数据
     *
     */
    @PutMapping("person/update")
    public ResponseEntity update(@RequestBody Person person){
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
            if (person.getName()!= null) {
                builder.field("name", person.getName());
            }
            if (person.getAge() != null) {
                builder.field("age", person.getAge());
            }
            if(person.getWork() != null){
                builder.field("work", person.getWork());
            }
            builder.endObject();

            String id = String.valueOf(person.getId());

            UpdateRequest updateRequest = new UpdateRequest("data", "person", id);
            updateRequest.doc(builder);
            UpdateResponse result = client.update(updateRequest).get();
            return new ResponseEntity(result.getResult().toString(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     *
     * 功能描述: 删除数据
     *
     */
    @DeleteMapping("person/delete")
    public ResponseEntity delete(@RequestParam(name = "id") String id) {
        DeleteResponse result = client.prepareDelete("data", "person", id).get();
        return new ResponseEntity(result.getResult().toString(), HttpStatus.OK);
    }


}
