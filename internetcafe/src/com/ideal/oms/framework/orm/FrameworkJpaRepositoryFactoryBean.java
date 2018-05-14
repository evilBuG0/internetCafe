package com.ideal.oms.framework.orm;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class FrameworkJpaRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
		extends JpaRepositoryFactoryBean<R, T, I> {
	protected RepositoryFactorySupport createRepositoryFactory(
			EntityManager entityManager) {
		return new FrameworkRepositoryFactory(entityManager);
	}

	private static class FrameworkRepositoryFactory<T, I extends Serializable>
			extends JpaRepositoryFactory {
		private EntityManager entityManager;

		public FrameworkRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		protected Object getTargetRepository(RepositoryMetadata metadata) {
			return new JpaRepositoryImpl<T, I>(
					(Class<T>) metadata.getDomainType(), entityManager);
		}

		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return JpaRepositoryImpl.class;
		}
	}
}
