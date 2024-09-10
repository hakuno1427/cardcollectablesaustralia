$(document).ready(function() {
    // Auto-select the first user and first subject when the page is loaded
    let firstUser = $('.user-item').first();
    firstUser.addClass('selected');
    loadSubjects(firstUser.data('userid'));
	scrollToBottom();

    // Fetch subjects when a user is clicked
    $('.user-item').click(function() {
        var userId = $(this).data('userid');
        
        // Highlight selected user
        $('.user-item').removeClass('selected');
        $(this).addClass('selected');

        loadSubjects(userId);
        $('#messageBox').html('');
        $('#messageForm').hide();
    });
	
	
	function scrollToBottom() {
	    var messageBox = document.getElementById('messageBox');
	    messageBox.scrollTop = messageBox.scrollHeight;
	}

    function loadSubjects(userId) {
        $.ajax({
            url: 'messageFetcher?action=getSubjects&userId=' + userId,
            type: 'GET',
            success: function(data) {
                $('#subjectList').html(data);
                
                // Auto-select the first subject after loading
                let firstSubject = $('.subject-item').first();
                if (firstSubject.length) {
                    firstSubject.addClass('selected');
                    loadMessages(firstSubject.data('userid'), firstSubject.data('subject'));
                }
            }
        });
    }

    // Fetch messages when a subject is clicked
    $(document).on('click', '.subject-item', function() {
        var userId = $(this).data('userid');
        var subject = $(this).data('subject');

        // Highlight selected subject
        $('.subject-item').removeClass('selected');
        $(this).addClass('selected');

        loadMessages(userId, subject);
    });

    function loadMessages(userId, subject) {
        $.ajax({
            url: 'messageFetcher?action=getMessages&userId=' + userId + '&subject=' + subject,
            type: 'GET',
            success: function(data) {
                $('#messageBox').html(data);
                $('#messageForm').show();
                $('#subject').val(subject);
                $('#userId').val(userId);
            }
        });
    }

    // Handle form submission
    $('#messageForm').submit(function(event) {
        event.preventDefault();
        var messageContent = $('#messageContent').val();
        var formattedMessageContent = messageContent.replace(/\n/g, '<br>');
        $('#messageContent').val(formattedMessageContent);

        var formData = $('#sendMessageForm').serialize();
        $.ajax({
            url: 'send_message',
            type: 'POST',
            data: formData,
            success: function(response) {
				var userId = $('#userId').val();
                var subject = $('#subject').val();
                loadMessages(userId, subject); // Reload messages after sending
                $('#messageContent').val(''); // Clear the form input
				setTimeout(scrollToBottom, 500);
            }
        });
    });
});