var createMenu = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            $("form").submit();
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