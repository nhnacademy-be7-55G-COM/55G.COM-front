const execPostCode = ({ zipSelector, addressSelector, addressDetailSelector, addressExtraSelector }) => {
  new daum.Postcode({
    oncomplete: data => {
      let addr = '';
      let extraAddr = '';

      if (data.userSelectedType === 'R') {
        addr = data.roadAddress;
      } else {
        addr = data.jibunAddress;
      }
      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if(data.userSelectedType === 'R'){
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
          extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if(data.buildingName !== '' && data.apartment === 'Y'){
          extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if(extraAddr !== ''){
          extraAddr = ' (' + extraAddr + ')';
        }
        // 조합된 참고항목을 해당 필드에 넣는다.
        document.querySelector(addressExtraSelector).value = extraAddr;

      } else {
        document.querySelector(addressExtraSelector).value = '';
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.querySelector(zipSelector).value = data.zonecode;
      document.querySelector(addressSelector).value = addr;
      // 커서를 상세주소 필드로 이동한다.
      document.querySelector(addressDetailSelector).focus();
    }
  }).open();
};

/*
  customer = { key, name, email }
 */

const cardPaymentDefault = {
  useEscrow: false,
  flowMode: 'DEFAULT',
  useCardPoint: false,
  useAppCardOnly: false
};

const tossPaymentReady = async ({
    orderId,
    orderName,
    method,
    amount,
    successUrl,
    failUrl,
    customer
}, clientKey) => {
  const tossPayments = TossPayments(clientKey);
  const customerKey = customer.key;
  const payment = tossPayments.payment({ customerKey });

  const paymentInfo = {
    method: method,
    amount: {
      currency: amount.currency,
      value: amount.value
    },
    orderId: orderId,
    orderName: orderName,
    successUrl: successUrl,
    failUrl: failUrl,
    customerEmail: customer.email,
    customerName: customer.name
  };

  if (method === 'CARD') {
    paymentInfo.card = structuredClone(cardPaymentDefault);
  }
  await payment.requestPayment(paymentInfo);
}

const returnToIndex = () => {
  window.location.href='/';
}

const fetchShoppingCart = async () => {
  return (await axios.get('/payment/support/fetch-cart')).data;
}

const createNewOrder = (deliveryInfo) => {
  return axios.post('/payment/support/create-order', deliveryInfo);
}

const isNotBlank = (...args) => {
  for (let i=0; i<args.length; i++) {
    if (args[i] === undefined || args[i] === '') {
      return false;
    }
  }
  return true;
}

const getDeliveryInformation = ({
    zipSelector,
    addressSelector,
    addressDetailSelector,
    addressExtraSelector
}, feeId, receiveDateSelector) => {
  const zip = document.querySelector(zipSelector).value;
  const address= document.querySelector(addressSelector).value;
  const addressDetail= document.querySelector(addressDetailSelector).value;
  const addressExtra= document.querySelector(addressExtraSelector).value;
  const receiveDate = document.querySelector(receiveDateSelector).value;

  // extra는 비어도 허용
  if (!isNotBlank(zip, address, addressDetail, receiveDate)) {
    throw "주소가 비어있음";
  }
  const addressString = `${zip}, ${address} ${addressDetail} ${addressExtra}`.trim();
  return {
    address: addressString,
    deliveryFeeId: feeId,
    receivedDate: receiveDate
  };
}