package com.tea101g3.service;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.tea101g3.entity.ListBean;
import com.tea101g3.DAO.*;

public class TodoService {
    TodoDao todoDao = new TodoDao();

    public void updateOne(ListBean updateBean) {
        todoDao.updateOne(updateBean);
        reOrderTodoList();
    }

    public void deleteOne(String srcId) {
        todoDao.deleteOne(srcId);
        reOrderTodoList();
    }

    void reOrderTodoList(){
        List<ListBean> list=todoDao.getAll();
        AtomicInteger aint = new AtomicInteger(0);
        list.forEach(bean->{
                bean.setOrderBy(aint.getAndIncrement());
                todoDao.updateOne(bean);
            });
    }

}
