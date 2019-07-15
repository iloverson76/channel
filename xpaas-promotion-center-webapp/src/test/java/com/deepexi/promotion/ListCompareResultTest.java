package com.deepexi.promotion;

import com.deepexi.promotion.common.ListCompareResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhoust
 * @date 2019/5/25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupApplication.class)
@ActiveProfiles("dev")
public class ListCompareResultTest {

    @Test
    public void test(){
        List<String> oldList = Stream.of("1", "2", "3", "4").collect(Collectors.toList());
        List<String> updateList = Stream.of("3", "4", "5").collect(Collectors.toList());
        ListCompareResult<String> compareResult = ListCompareResult.compareLists(oldList,updateList,
                String::toString,
                (oldOne,updateOne)-> oldOne.equals(updateOne));
        List<String> toCreate = compareResult.getToCreate();
        List<String> toUpdate = compareResult.getToUpdate();
        List<String> toDelete = compareResult.getToDelete();
        List<String> create = Stream.of("5").collect(Collectors.toList());
        List<String> update = Stream.of("3", "4").collect(Collectors.toList());
        List<String> delete = Stream.of("1","2").collect(Collectors.toList());
        Assert.assertTrue(toCreate.equals(create));
        Assert.assertTrue(toUpdate.equals(update));
        Assert.assertTrue(toDelete.equals(delete));
    }
}
