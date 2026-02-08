## Noted for ingress 

- we must have deployment for put domain name
---

We use NodePort not secure cuz it expose port to outside, so in production we use ClusterIP.

---

when we see connection refush, mean it can't find pod to run service.

---

We have two way for put domain name : 
- host base : ត្រូវការ domain name មួយ សម្រាប់ service មួយ
- path base : ត្រូវការ domain name មួយ តែត្រូវកំណត់ path ដើម្បី point ទៅកាន់ service 