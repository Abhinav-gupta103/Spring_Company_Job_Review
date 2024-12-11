package com.naukri.job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Job addJob(Job job);

    List<Job> getJobs();

    Optional<Job> getJobById(Long id);

    String deleteJobById(Long id);

    Boolean updateJobById(Long id, Job updatedJob);
}
