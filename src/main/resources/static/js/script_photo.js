//let cropper;
//let cropperModalId = '#cropperModal';
//let $jsPhotoUploadInput = $('.js-photo-upload');
//
//$jsPhotoUploadInput.on('change', function () {
//  var files = this.files;
//  if (files.length > 0) {
//    var photo = files[0];
//
//    var reader = new FileReader();
//    reader.onload = function (event) {
//      var image = $('.js-avatar-preview')[0];
//      image.src = event.target.result;
//
//      cropper = new Cropper(image, {
//        viewMode: 1,
//        aspectRatio: 1,
//        minContainerWidth: 400,
//        minContainerHeight: 400,
//        minCropBoxWidth: 271,
//        minCropBoxHeight: 271,
//        movable: true,
//        ready: function () {
//          console.log('ready');
//          console.log(cropper.ready);
//        } });
//
//
//      $(cropperModalId).modal();
//    };
//    reader.readAsDataURL(photo);
//  }
//});
//
//$('.js-save-cropped-avatar').on('click', function (event) {
//  event.preventDefault();
//
//  console.log(cropper.ready);
//
//  var $button = $(this);
//  $button.text('uploading...');
//  $button.prop('disabled', true);
//
//  const canvas = cropper.getCroppedCanvas();
//  const base64encodedImage = canvas.toDataURL();
//  $('#avatar-crop').attr('src', base64encodedImage);
//  $(cropperModalId).modal('hide');
//});



//otro///////////////////////////////

function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
      $('#blah').attr('src', e.target.result)
    };
    reader.readAsDataURL(input.files[0]);
    setTimeout(initCropper, 1000);
  }
}
function initCropper(){
  console.log("Came here")
  var image = document.getElementById('blah');
  var cropper = new Cropper(image, {
    aspectRatio: 3 / 3,
    crop: function(e) {
      console.log(e.detail.x);
      console.log(e.detail.y);
    }
  });

  // On crop button clicked
  document.getElementById('crop_button').addEventListener('click', function(){
    var imgurl =  cropper.getCroppedCanvas().toDataURL();
    var img = document.createElement("img");
    img.src = imgurl;
    document.getElementById("cropped_result").appendChild(img);

    /* ---------------- SEND IMAGE TO THE SERVER-------------------------

                cropper.getCroppedCanvas().toBlob(function (blob) {
                      var formData = new FormData();
                      formData.append('croppedImage', blob);
                      // Use `jQuery.ajax` method
                      $.ajax('/path/to/upload', {
                        method: "POST",
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function () {
                          console.log('Upload success');
                        },
                        error: function () {
                          console.log('Upload error');
                        }
                      });
                });
            ----------------------------------------------------*/
  })
}