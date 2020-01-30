
var createMenu = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            $("form").submit();
        });

        $('#btn-cancel').on('click', function (e) {
            e.preventDefault();
            window.location.href = "/easyAdmin/";
        });

        $('#btn-delete').on('click', function (e) {
            e.preventDefault();
            var eto_id = $("#eto_id").val();
            window.location.href = "/easyAdmin/deleteMenu/"+eto_id;
        });

        $(".close").on('click', function () {
            $('#foo').attr('src', '#');
        });

        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('#foo').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }

        $("#file").change(function() {
            readURL(this);
        });



    }

};

createMenu.init();