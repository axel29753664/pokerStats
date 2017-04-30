angular.module("mainApp")
    .factory("regValidator", function () {
        return {
            validate: function (newUser) {
                var errors = {
                    hasError: false
                };
                var name = [];
                var password = [];
                var passwordRepeat = [];


                if (newUser.name.length < 3) {
                    errors.hasError = true;
                    name.push("Логин должен быть больше 3 символов");
                }

                if (newUser.password.length < 3) {
                    errors.hasError = true;
                    password.push("Пароль должен быть больше 3 символов");
                }
                if (newUser.passwordRepeat.length < 3) {
                    errors.hasError = true;
                    passwordRepeat.push("Пароль должен быть больше 3 символов");
                }
                if (newUser.password !== newUser.passwordRepeat) {
                    errors.hasError = true;
                    password.push('Пароли не одинаковые !');
                    passwordRepeat.push('Пароли не одинаковые !');
                }

                if (name.length > 0) {
                    errors.name = name;
                }
                if (password.length > 0) {
                    errors.password = password;
                }
                if (passwordRepeat.length > 0) {
                    errors.passwordRepeat = passwordRepeat;
                }
                return errors;
            }
        }
    });
