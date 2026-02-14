## Noted 

This is use for Core learning and Tools, technology about cloudflare.

### 1. Cloudflare Tunnel

![cloudflare-tunnel](https://developers.cloudflare.com/_astro/handshake.eh3a-Ml1_1IcAgC.webp)

> Example :
>
> If we want to host a website or service like firewall(Port Forwarding) and expose home IP address to the world. Cloudflare Tunnel eliminates that risk entirely.

Instead of waiting for the internet to reach out to your server, a small piece of software called cloudflared runs on your server and creates an outbound-only connection to Cloudflare’s nearest data center.


* No Port Forwarding
* Security (The "Invisible" Server)
* Works Behind CGNAT(Carrier-Grade Network Address Translation)
* Zero Trust Integration: You can easily add a login page (like Google, GitHub, or Okta) in front of your private applications without writing a single line of authentication code.


1. The Core Component:

* The Handshake: When you start cloudflared, it establishes(កាបង្កើត) four persistent, long-lived connections to different Cloudflare data centers (edge nodes).
* The Protocol: It typically uses QUIC (built on UDP), which is designed to be fast and resilient if a specific network path gets congested.
* The Result: Your server is now "mesh-connected" to the Cloudflare global network.

2. Traffic Flow: The "Reverse Proxy" like `Magic`

    When a user types your URL (e.g, app.domain.com): The browser asks where the site is. Cloudflare’s DNS points it to the nearest Cloudflare Edge server. (DNS route ទៅកាន់ server មួយណាដែលនៅជិត)
    * **DNS Resolution**: The browser asks where the site is. Cloudflare’s DNS points it to the nearest Cloudflare Edge server.
    * **The Edge Encounter**: The request hits the Cloudflare Edge. This is where the heavy lifting happens (WAF filtering, DDoS protection, Bot management).
    * *The Tunnel Route*: Instead of Cloudflare trying to find your home IP address, it looks for the active "pipe" (cloudflared) that matches your Tunnel ID.
    * *Local Delivery*: Cloudflare pushes the request down through that established outbound pipe. cloudflared receives it locally and passes it to your web service (like a local port $8080$).The Response: Your app responds to cloudflared, which sends the data back up the pipe to the user.
