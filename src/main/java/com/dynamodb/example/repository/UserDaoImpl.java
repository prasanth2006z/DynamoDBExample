package com.dynamodb.example.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.dynamodb.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: pxp167
 * @Date: 4/4/2019
 *
 */
@Repository
public class UserDaoImpl {

    @Autowired
    private DynamoDBMapper mapper;

    public void saveUser(User user) {
        mapper.save(user);
    }

    public void update(User user) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expected = new HashMap<String, ExpectedAttributeValue>();
        expected.put("User_ID", new ExpectedAttributeValue(new AttributeValue(user.getUserId()))
          .withComparisonOperator(ComparisonOperator.EQ));
        saveExpression.setExpected(expected);
        mapper.save(user, saveExpression);
    }

    public void delete(User user) {
        mapper.delete(user);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression().withLimit(50);
        final PaginatedScanList<User> paginatedList = mapper.scan(User.class, dynamoDBScanExpression);
        for (User u : paginatedList) {
            users.add(u);
        }
        return users;
    }
}
