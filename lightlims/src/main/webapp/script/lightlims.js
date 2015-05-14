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

    var jobCache = [];

    var client = Stomp.over(new SockJS("/resource"));
    client.connect({}, function(frame) {
        console.log('SockJS: ' + frame.command);
        client.subscribe('/job', function(msg){
            alert(msg);
        });
    });

});