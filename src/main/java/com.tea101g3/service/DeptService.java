package com.tea101g3.service;

import java.util.List;
import java.util.Set;

import com.tea101g3.DAO.DeptDAO;
import com.tea101g3.DAO.DeptDAO_interface;
import com.tea101g3.entity.DeptVO;
import com.tea101g3.entity.EmpVO;

public class DeptService {

	private DeptDAO_interface dao;

	public DeptService() {
		dao = new DeptDAO();
	}

	public List<DeptVO> getAll() {
		return dao.getAll();
	}

	public DeptVO getOneDept(Integer deptno) {
		return dao.findByPrimaryKey(deptno);
	}

	public Set<EmpVO> getEmpsByDeptno(Integer deptno) {
		return dao.getEmpsByDeptno(deptno);
	}

	public void deleteDept(Integer deptno) {
		dao.delete(deptno);
	}
}
