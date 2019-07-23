var login = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            email: $('#email').val(),
            password: $('#password').val()
        };

        if (data.email == '' || data.password == '') return false;

        $("#login_form").action('/admin/login');
        $("#login_form").submit();
        // $.ajax({
        //     type: 'POST',
        //     url: '/admin/login',
        //     dataType: 'json',
        //     contentType:'application/json; charset=utf-8',
        //     data: JSON.stringify(data)
        // }).done(function() {
        //     alert('가입되었습니다.');
        //     location.href="/admin/login"
        // }).fail(function (error) {
        //     alert(error);
        // });
    }

};

login.init();