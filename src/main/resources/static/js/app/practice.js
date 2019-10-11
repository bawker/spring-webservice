var practice = {
    init : function () {
        var _this = this;
        $("#btn_submit").on('click', function(){
            _this.save();
        });

        $("#delete_btn").on('click', function(e){
            e.preventDefault();
            alert($(this));
            return false;
        })

    },
    save : function () {
        var data = $("form").serialize();
        var url = $("form").attr('action');
        console.log(data);
        alert(data);

        $.ajax({
            type: 'post',
            url: url,
            dataType: 'json',
            // contentType:'application/json; charset=utf-8',
            data: data
        }).done(function(result) {
            alert('등록 되었습니다.');

            console.log(result);

            var answerTemplate = $("#answerTemplate").html();
            var CreateDate = result.createdDate.year + "-" + result.createdDate.monthValue + "-" + result.createdDate.dayOfMonth + " "
                + result.createdDate.hour + ":" + result.createdDate.minute + ":" + result.createdDate.second;

            var template = answerTemplate.format(result.id, result.contents, result.writer.name, CreateDate, result.id);

            $("#answer_table").append(template);

            $("textarea[name=contents]").val("");

            // location.href="/questions/detail/{questionId}"
        }).fail(function (error) {
            alert("실패")
            console.log(error);
        });
    },

}

function delete_answer(qna_id, answer_id) {
    return false;
    var url = '/api/questions/' + qna_id +'/answers/' + answer_id;
    // var delete_domain = id;
    console.log(this.closest("tr"));
    return false;
    $.ajax({
        type: 'delete',
        url: url,
        dataType: 'json'
    }).done(function(result){
        if(result.result == 'fail') {
            alert(result.massage);
        }
        if(result.result == 'success') {
            console.log(delete_domain.html());
            return false;
            delete_domain.closest("tr").remove();
        }
    }).fail(function(error){
        console.log(error);
    });
}
String.prototype.format = function() {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function(match, number) {
        return typeof args[number] != 'undefinded' ? args[number] : match;
    });
};

practice.init();