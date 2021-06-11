package com.show.sign.mapper;

import com.show.sign.pojo.Buka;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@RegisterMapper
public interface BukaMapper extends Mapper<Buka> {

    /**
     * 根据stuid查询7天内补卡记录
     * @param stuid
     * @return
     */
    @Select("SELECT id,stuid,datime  WHERE ((curdate() - interval 6 day) <= `datime`) and stuid=#{stuid} ")
    List<Buka> get7BukaBystuid(@Param("stuid") Integer stuid);
}
