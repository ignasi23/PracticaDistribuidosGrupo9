$(document).ready(function() {
    // Function to fetch and update report table content
    function fetchAndUpdateReportTableContent() {
        $.ajax({
            url: '/get_reports', // Replace with the URL of your server-side endpoint that returns updated reports
            method: 'GET', // Use 'GET' or 'POST' as appropriate
            dataType: 'json', // Expect JSON response
            success: function(response) {
                // Clear existing table rows
                $('#report-table-body').empty();

                // Loop through the response data and append new rows to the table
                $.each(response, function(index, report) {
                    console.log(report);

                    for (var i = 0; i < report.length; i++) {
                        var row = '<tr>';
                        row += '<td>' + report[i][0] + '</td>' +
                            '<td>' + report[i][1] + '</td>' +
                            '<td>' + report[i][2] + '</td>';
                        row += '</tr>';
                        $('#report-table-body').append(row);
                    }


                });
            },
            error: function(xhr, status, error) {
                // Handle any errors that occur during AJAX request
                console.error(error);
            }
        });
    }

    // Fetch and update report table content initially
    fetchAndUpdateReportTableContent();

    // Periodically fetch and update report table content every 5 seconds (5000 milliseconds)
    setInterval(fetchAndUpdateReportTableContent, 5000);
});
