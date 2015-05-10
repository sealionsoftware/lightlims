$( document ).ready(function() {

    $.ajax({
        url: "/resource/job"
    }).then(function(jobs) {
        jobs.forEach(function(job){
            $('.jobSummaries').append(
                $('.templates .jobSummary').clone().each(function(){
                    $(this).find('.code').html(job.code);
                    $(this).find('.description').html(job.description);
                })
            )
        });
    });

});