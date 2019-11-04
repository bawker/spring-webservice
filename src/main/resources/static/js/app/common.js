var common = {
    init : function () {
        var _this = this;
        $(".a_logout").on('click', function () {
            $("form[name=logout_form]").submit();
        });
    }

};

common.init();