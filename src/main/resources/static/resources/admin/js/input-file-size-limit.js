$(document).ready(function() {
    initFileSizeLimit('.js-file-input-size-limit-div');
});

function initFileSizeLimit(inputParentDivSelector) {
    $(`${inputParentDivSelector} [type="file"]`).change(function() {
       let maxSizeStr = $(this).data('max-size');

       let maxSize;
       if (maxSizeStr.endsWith('GB')) {
           maxSize = parseFloat(maxSizeStr.replace('GB', '')) * 1024 * 1024 * 1024;
       } else if (maxSizeStr.endsWith('MB')) {
           maxSize = parseFloat(maxSizeStr.replace('MB', '')) * 1024 * 1024;
       } else if (maxSizeStr.endsWith('KB')) {
           maxSize = parseFloat(maxSizeStr.replace('KB', '')) * 1024;
       } else {
           // Assumes that the value is in bytes without 'GB', 'MB' or 'KB'
           maxSize = parseFloat(maxSizeStr);
       }

       const file = this.files[0];

       if (file.size > maxSize) {
           $(this).siblings('.js-file-input-size-exceeded-feedback').removeClass('d-none').addClass('d-block');
           this.value = ''; // Clears the input value
       } else {
           $(this).siblings('.js-file-input-size-exceeded-feedback').removeClass('d-block').addClass('d-none');
       }
    });
}
