var app = angular.module("app", []);

app.controller("ctrl", function ($scope, $http) {
    $scope.import = function (files) {
        if (files && files.length > 0) {
            var file = files[0];
            var reader = new FileReader();

            reader.onload = async function (e) {
                try {
                    var workbook = new ExcelJS.Workbook();
                    await workbook.xlsx.load(e.target.result); // Load file Excel
                    const worksheet = workbook.getWorksheet('Sheet1');

                    worksheet.eachRow((row, index) => {
                        if (index > 1) {
                            let student = {
                                email: row.getCell(1).text,
                                fullname: row.getCell(2).text,
                                marks: row.getCell(3).value,
                                gender: row.getCell(4).value === 'true',
                                country: row.getCell(5).text
                            };

                            // Gửi yêu cầu POST tới REST API
                            var url = "http://localhost:8080/rest/students";
                            $http.post(url, student).then(resp => {
                                console.log("Success", resp.data);
                            }).catch(error => {
                                console.error("Error", error);
                            });
                        }
                    });
                } catch (error) {
                    console.error("Error reading Excel file:", error);
                }
            };

            reader.readAsArrayBuffer(file); // Đọc file Excel dưới dạng ArrayBuffer
        } else {
            console.error("No file selected");
        }
    };
});
