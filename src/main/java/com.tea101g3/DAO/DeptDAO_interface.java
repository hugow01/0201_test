package com.tea101g3.DAO;

import java.util.List;
import java.util.Set;

import com.tea101g3.entity.DeptVO;
import com.tea101g3.entity.EmpVO;

public interface DeptDAO_interface {
	      public void insert(DeptVO deptVO);
          public void update(DeptVO deptVO);
          public void delete(Integer deptno);
          public DeptVO findByPrimaryKey(Integer deptno);
	      public List<DeptVO> getAll();
	      //�d�߬Y���������u(�@��h)(�^�� Set)
	      public Set<EmpVO> getEmpsByDeptno(Integer deptno);
}
