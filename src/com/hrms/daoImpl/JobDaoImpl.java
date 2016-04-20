package com.hrms.daoImpl;

import java.util.List;





import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.hrms.bDhelper.DBhelper;
import com.hrms.dao.JobDao;
import com.hrms.model.Job;

public class JobDaoImpl implements JobDao{
	EntityManagerFactory emf = null;
	EntityManager em = null;
	@Override
	public Long saveJob(Job job) {
		emf=DBhelper.provideEntity();
		em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(job);
		em.getTransaction().commit();
		em.close();	
		return job.getJobId();
	}

	@Override
	public Long updateJob(Job job) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Job searchJob(Long jobId) {
		emf = DBhelper.provideEntity();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Job job = em.find(Job.class,jobId);
		return job;
	}

	@Override
	public List<Job> jobList() {
		 emf = DBhelper.provideEntity();
		 em = emf.createEntityManager();
		 TypedQuery<Job> query = em.createQuery("from Job job ",Job.class);
		 List<Job> results = query.getResultList();
		 return results;
	}
}
