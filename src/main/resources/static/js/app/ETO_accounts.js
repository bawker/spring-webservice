var ETOaccount = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            $("#station_nm").val($("#station_cd option:selected").text());
            //var data = $("form").serialize();
            $("form").submit();
        });



        $("#subwayByLine").on('change', function(){
            // var data = {};
            // data["line"] = $(this).val();

            var data = {
                line: $(this).val()
            };

            $.ajax({
                type: 'POST',
                url: '/api/easyAdmin/subwayInfo',
                dataType: 'json',
                contentType:'application/json;',
                data: JSON.stringify(data)
            }).done(function(res) {
                var parsedJson = JSON.parse(res.result);
                $("#station_cd").html("");
                $.each(parsedJson.SearchSTNBySubwayLineInfo.row, function (index, item) {
                    $("#station_cd").append("<option value="+item.STATION_CD+">"+item.STATION_NM+"</option>");
                });

            }).fail(function (error) {
                console.log(error);
            });
        })

    },
    // save : function () {
    //     var data = {
    //         name: $('#name').val(),
    //         email: $('#email').val(),
    //         password: $('#password').val()
    //     };
    //
    //     $.ajax({
    //         type: 'POST',
    //         url: '/admin/createAccount',
    //         dataType: 'json',
    //         contentType:'application/json; charset=utf-8',
    //         data: JSON.stringify(data)
    //     }).done(function() {
    //         alert('가입되었습니다.');
    //         location.href="/admin/login"
    //     }).fail(function (error) {
    //         alert(error);
    //     });
    // }

};

ETOaccount.init();