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

//function readURL(input) {
//  if (input.files && input.files[0]) {
//    var reader = new FileReader();
//    reader.onload = function (e) {
//      $('#blah').attr('src', e.target.result)
//    };
//    reader.readAsDataURL(input.files[0]);
//    setTimeout(initCropper, 1000);
//  }
//}
//function initCropper(){
//  console.log("Came here")
//  var image = document.getElementById('blah');
//  var cropper = new Cropper(image, {
//    aspectRatio: 3 / 3,
//    crop: function(e) {
//      console.log(e.detail.x);
//      console.log(e.detail.y);
//    }
//  });
//
//  // On crop button clicked
//  document.getElementById('crop_button').addEventListener('click', function(){
//    var imgurl =  cropper.getCroppedCanvas().toDataURL();
//    var img = document.createElement("img");
//    img.src = imgurl;
//    document.getElementById("cropped_result").appendChild(img);
//
//    /* ---------------- SEND IMAGE TO THE SERVER-------------------------
//
//                cropper.getCroppedCanvas().toBlob(function (blob) {
//                      var formData = new FormData();
//                      formData.append('croppedImage', blob);
//                      // Use `jQuery.ajax` method
//                      $.ajax('/path/to/upload', {
//                        method: "POST",
//                        data: formData,
//                        processData: false,
//                        contentType: false,
//                        success: function () {
//                          console.log('Upload success');
//                        },
//                        error: function () {
//                          console.log('Upload error');
//                        }
//                      });
//                });
//            ----------------------------------------------------*/
//  })
//}


////////////////////////////////////////////////////////////////////////////

//var Resample = (function (canvas) {
//
//	// (C) WebReflection Mit Style License
//
//  function Resample(img, width, height, onresample) {
//  
//    var load = typeof img == "string",
//    i = load || img;
//
//    if (load) {
//     i = new Image;
//     // with propers callbacks
//     i.onload = onload;
//     i.onerror = onerror;
//    }
//
//    i._onresample = onresample;
//    i._width = width;
//    i._height = height;
//
//    load ? (i.src = img) : onload.call(img);
//   }
//
//   function onerror() {
//    throw ("not found: " + this.src);
//   }
//
//   function onload() {
//    var
//     img = this,
//     width = img._width,
//     height = img._height,
//     onresample = img._onresample
//    ;
//
//    // Altered section - crop prior to resizing
//    var imgRatio = img.width / img.height;
//    var desiredRatio = width / height;
//    var cropWidth, cropHeight;
//    if (desiredRatio < imgRatio) {
//      cropHeight = img.height;
//      cropWidth = img.height * desiredRatio;
//    } else {
//      cropWidth = img.width;
//      cropHeight = img.width / desiredRatio;
//    }
//
//    delete img._onresample;
//    delete img._width;
//    delete img._height;
//
//    canvas.width = width;
//    canvas.height = height;
//
//    context.drawImage(
//     // original image
//     img,
//     // starting x point
//     0,
//     // starting y point
//     0,
//     // crop width
//     cropWidth,
//     // crop height
//     cropHeight,
//     // destination x point
//     0,
//     // destination y point
//     0,
//     // destination width
//     width,
//     // destination height
//     height
//    );
//
//    onresample(canvas.toDataURL("image/png"));
//   }
// 
//	var context = canvas.getContext("2d"),
//  round = Math.round;
//
//  return Resample;
// 
//}(
// this.document.createElement("canvas"))
//);
//
//var newCropWidth = 300;
//var newCropHeight = 300;
//
//function loadImage(data) {
//  document.querySelector('#imgDisplay').src = data;
//}
//function handleFileSelect(evt) {
//  if (evt.target.files.length === 1) {
//    var picFile = evt.target.files[0];
//
//    if (picFile.type.match('image.*')) {
//      var fileTracker = new FileReader;
//      fileTracker.onload = function() {
//        Resample(
//         this.result,
//         newCropWidth,
//         newCropHeight,
//         loadImage
//       );
//      }
//      fileTracker.readAsDataURL(picFile);
//    }
//  }
//}
//
//document.querySelector('#uploadImage').addEventListener('change', handleFileSelect, false);

///////////////////////////////////////////////////////////////////////////////////////

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

  // Al hacer clic en el botón de recorte
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