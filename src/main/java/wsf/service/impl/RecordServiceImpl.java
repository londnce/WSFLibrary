package wsf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import wsf.domain.Record;
import wsf.domain.User;
import wsf.entity.PageResult;
import wsf.mapper.RecordMapper;
import wsf.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;
    // 添加借阅记录
    @Override
    public Integer addRecord(Record record) {
        return recordMapper.addRecord(record);
    }

    //查询借阅记录
    @Override
    public PageResult searchRecords(Record record, User user, Integer pageNum, Integer pageSize) {
        // 设置分页查询的参数，开始分页
        PageHelper.startPage(pageNum, pageSize);
        //如果不是管理员，则查询条件中的借阅人设置为当前登录用户
        if(!"ADMIN".equals(user.getRole())){
            record.setBorrower(user.getName());
        }
        Page<Record> page= recordMapper.searchRecords(record);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
