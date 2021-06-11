package com.show.sign.service.open;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.show.sign.mapper.RequireMapper;
import com.show.sign.pojo.Adminuser;
import com.show.sign.pojo.Exporttask;
import com.show.sign.pojo.Require;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class RequireService {
 
	@Resource
	private RequireMapper requireMapper;
 
	
	public int addRequire(Require require){
		return requireMapper.insert(require);
	}
	
	@Transactional
	public int editRequire(Require require){
		Example example = Example.builder(Require.class).build();
		Example.Criteria c=example.createCriteria();
		c.andEqualTo("request",  require.getRequest());

		return requireMapper.updateByExampleSelective(require,example);
	}

	public int formatRequire(Require require){
		Example example = Example.builder(Require.class).build();
		Example.Criteria c=example.createCriteria();
		c.andEqualTo("auto",  1);
		Require req=new Require();
		req.setAuto(0);
		int datas = requireMapper.updateByExampleSelective(req,example);
		return datas;
	}
	
	public int deleteRequire(Require require) {
		Example example = Example.builder(Require.class).build();
		Example.Criteria c=example.createCriteria();
		c.andEqualTo("request",  require.getRequest());
		c.andEqualTo("response",  require.getResponse());
		c.andEqualTo("auto",  require.getAuto());

		int datas = requireMapper.deleteByExample(example);
		return datas;
	}
	
	public Require getRequireByRequest(Require require){
		Example example = Example.builder(Require.class).build();
		Example.Criteria c=example.createCriteria();
		c.andEqualTo("request",  require.getRequest());

		List<Require> datas = requireMapper.selectByExample(example);
		if(datas!=null&&datas.size()>0){
			return datas.get(0);
		}else{
			return null;
		}
	}
	
	public List<Require> getRequireByAuto(Require require){
		Example example = Example.builder(Require.class).build();
		Example.Criteria c=example.createCriteria();
		c.andEqualTo("auto",  require.getAuto());
		List<Require> datas = requireMapper.selectByExample(example);
		return datas;
	}
	
	public Require getRequireByRequest(String request){
		Example example = Example.builder(Require.class).build();
		Example.Criteria c=example.createCriteria();
		c.andEqualTo("request",  request);
		List<Require> datas = requireMapper.selectByExample(example);
		if(datas!=null&&datas.size()>0){
			return datas.get(0);
		}else{
			return null;
		}
	}
	
	public PageInfo<Require> getRequires(String request,Integer auto,Integer page, Integer limit){
		Example example = Example.builder(Require.class).build();
		Example.Criteria c=example.createCriteria();
		if(auto!=null&&!auto.equals(0)){
			c.andEqualTo("auto",auto);
		}
		if(request!=null&&!request.equals("")){
			c.andLike("request","%"+request+"%");
		}

		Page<Require> p = PageHelper.startPage(page, limit);
		List<Require> datas = requireMapper.selectByExample(example);
		PageInfo pageInfo = new PageInfo<>(datas);
		return pageInfo;
	}
	

}
