package soft.progress.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soft.progress.assignment.entity.Deal;

public interface DealRepository extends JpaRepository<Deal, Integer> {
}
