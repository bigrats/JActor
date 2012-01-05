/*
 * Copyright 2011 Bill La Forge
 *
 * This file is part of AgileWiki and is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License (LGPL) as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 * or navigate to the following url http://www.gnu.org/licenses/lgpl-2.1.txt
 *
 * Note however that only Scala, Java and JavaScript files are being covered by LGPL.
 * All other files are covered by the Common Public License (CPL).
 * A copy of this license is also included and can be
 * found as well at http://www.opensource.org/licenses/cpl1.0.txt
 */

/**
 * <p>
 * The JActor project implements actors in Java that can process .2 Billion messages per second.
 * Project pages can be found on <a href="http://sourceforge.net/p/jactor/home/Home/">Sourceforge</a>
 * and <a href="https://github.com/laforge49/JActor">GitHub</a>.
 * </p>
 * <p>
 * This project is a reimplementation of a portion of the Scala project,
 * <a href="https://github.com/laforge49/Asynchronous-Functional-Programming/wiki">AsyncFP</a>.
 * </p>
 * <p>
 * Message passing between actors uses 2-way messages (request / response). There are several reasons for this:
 * </p>
 * <ul>
 * <li>
 * With 2-way messaging, sending a request is very similar to a method call with a callback. Most requests are
 * processed synchronously, which is why JActor is so much faster than other actor implementations.
 * </li>
 * <li>
 * Mailboxes are used mostly when passing messages between threads and are first-class objects. As first-class
 * objects, mailboxes can be used my more than one actor. Passing messages between actors with a common mailbox
 * is always done synchronously and is very fast.
 * </li>
 * <li>
 * Flow control is implicit to 2-way messaging. Systems with good flow control are generally well-behaved when
 * operating with a full load.
 * </li>
 * <li>
 * Two-way messaging is so much faster than 1-way messaging that it is practical to use 2-way messages when 1-way
 * messages are needed.
 * </li>
 * </ul>
 * <h4>
 * Exception Handling
 * </h4>
 * <p>
 * The extensive use of callbacks complicates control flow, which is only made worse with some callbacks being executed
 * asynchronously. Exception trapping then can be quite error prone. So exception handling is supported. A default
 * exception handler is also provided which passes any uncaught exceptions that occurred while processing a request
 * back to the actor which sent the request, recursively.
 * </p>
 * <h4>
 * Bi-Modal Iterator
 * </h4>
 * <p>
 * Loops with 2-way messages can be problematic, as iterations typically must wait for the response from the previous
 * iteration. A bi-modal iterator is provided to cover this. Each iteration takes 5 nanoseconds for synchronous
 * responses and 8 nanoseconds when a response is asynchronous.
 * </p>
 * <h4>
 * State Machine
 * </h4>
 * <p>
 * State machines are often used with actors and can add considerable clarity to the code. JActor includes classes for
 * composing and executing state machines that are compatible with 2-way messages.
 * </p>
 * <h2>
 * Message Passing Benchmarks
 * </h2>
 * <p>
 * When actors share the same mailbox, 241,327,300 messages are passed per second. Otherwise the rate drops to
 * 293,040,293 per second.
 * </p>
 * <p>
 * Asynchronous message passing is also supported, making it easy to use all the available hardware threads for
 * good vertical scalability. Request messages sent to an actor with an asynchronous mailbox (and the corresponding
 * responses) are passed asynchronously at a rate of 51,712,992 per second.
 * </p>
 * <p>
 * Tests were done on an Intel Core i5 CPU M 540 @ 2.53GHz, which has 4 hardware threads.
 * The times reported were best run in 5. Only standard switch settings were used--there was
 * NO compiler optimization.
 * </p>
 */

package org.agilewiki.jactor;
