package com.naukri.job;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {
    private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job addJob(Job job) {
        return this.jobRepository.save(job);
    }

    @Override
    public List<Job> getJobs() {
        return this.jobRepository.findAll();
    }

    @Override
    public Optional<Job> getJobById(Long id) {
        return this.jobRepository.findById(id);
    }

    @Override
    public String deleteJobById(Long id) {
        Optional<Job> job = this.jobRepository.findById(id);
        if (job.isPresent()) {
            this.jobRepository.deleteById(id);
            return "Job Deleted";
        }
        return "Job Not Found";
    }

    @Override
    public Boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = this.jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setLocation(job.getLocation());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            this.jobRepository.save(job);
            return true;
        }
        return false;
    }
}
