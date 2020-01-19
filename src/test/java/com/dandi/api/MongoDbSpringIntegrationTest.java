package com.dandi.api;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObjectBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@DataMongoTest
@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
public class MongoDbSpringIntegrationTest {

	  @DisplayName("given object to save"
		        + " when save object using MongoDB template"
		        + " then object is saved")
		    @Test
		    public void test(@Autowired MongoTemplate mongoTemplate) {
		        // given
		        DBObject objectToSave = BasicDBObjectBuilder.start()
		            .add("key", "value")
		            .get();
		 
		        // when
		        mongoTemplate.save(objectToSave, "collection");
		        List<DBObject> records=mongoTemplate.findAll(DBObject.class, "collection");
		 
		        assertEquals(records.size(), 1);
		    }
}
